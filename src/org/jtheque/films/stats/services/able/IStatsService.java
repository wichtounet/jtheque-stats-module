package org.jtheque.films.stats.services.able;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.films.stats.services.impl.utils.StatsInformations;

/**
 * A service for the statistics.
 *
 * @author Baptiste Wicht
 */
public interface IStatsService {
    /**
     * Calculate and returns the titles of the stats.
     *
     * @return An array of titles.
     */
    String[] getTitles();

    /**
     * Refresh the statistics.
     */
    void refreshStats();

    /**
     * Return the statistics information.
     *
     * @return The statistics information.
     */
    StatsInformations getInformations();
}