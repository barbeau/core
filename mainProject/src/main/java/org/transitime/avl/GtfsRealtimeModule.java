/* 
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Transitime.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */
package org.transitime.avl;

import java.util.List;

import org.transitime.config.StringConfigValue;
import org.transitime.db.structs.AvlReport;
import org.transitime.feed.gtfsRt.GtfsRtVehiclePositionsReader;

/**
 * For reading in feed of GTFS-realtime AVL data. Is used for both realtime
 * feeds and for when reading in a giant batch of data.
 * 
 * @author SkiBu Smith
 * 
 */
public class GtfsRealtimeModule extends AvlModule {

	/*********** Configurable Parameters for this module ***********/
	public static String getGtfsRealtimeURI() {
		return gtfsRealtimeURI.getValue();
	}
	private static StringConfigValue gtfsRealtimeURI =
			new StringConfigValue("transitime.avl.gtfsRealtimeFeedURI", 
					"file:///C:/Users/Mike/gtfsRealtimeData",
					"The URI of the GTFS-realtime feed to use.");

	/********************** Member Functions **************************/

	/**
	 * @param projectId
	 */
	public GtfsRealtimeModule(String projectId) {
		super(projectId);
	}

	/**
	 * Reads and processes the data. Called by AvlModule.run().
	 */
	@Override
	protected void getAndProcessData() {
		List<AvlReport> avlReports = GtfsRtVehiclePositionsReader
				.getAvlReports(getGtfsRealtimeURI());
		for (AvlReport avlReport : avlReports) {
			processAvlReport(avlReport);
		}
	}

}
