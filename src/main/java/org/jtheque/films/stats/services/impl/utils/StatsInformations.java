package org.jtheque.films.stats.services.impl.utils;

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

import org.jfree.data.general.DefaultPieDataset;

/**
 * The statistics information.
 *
 * @author Baptiste Wicht
 */
public interface StatsInformations {
    /**
     * Return the total number of films.
     *
     * @return The number of films
     */
    int getNumberOfFilms();

    /**
     * Return the total number of actors.
     *
     * @return The number of actors
     */
    int getNumberOfActors();

    /**
     * Return the total number of realizers.
     *
     * @return The number of realizers
     */
    int getNumberOfRealizers();

    /**
     * Return the total duration of all the films.
     *
     * @return The duration of the films
     */
    int getTotalDurationOfFilms();

    /**
     * Return the stats of the films by kind.
     *
     * @return A dataset containing the stats of the films by kind
     */
    DefaultPieDataset getStatsOfFilmByKind();

    /**
     * Return the stats of the films by type.
     *
     * @return A dataset containing the stats of the films by type
     */
    DefaultPieDataset getStatsOfFilmByType();

    /**
     * Return the stats of the films by note.
     *
     * @return A dataset containing the stats of the films by note
     */
    DefaultPieDataset getStatsOfFilmByNote();

    /**
     * Return the stats of the actors by country.
     *
     * @return A dataset containing the stats of the actors by country
     */
    DefaultPieDataset getStatsOfActorsByCountry();

    /**
     * Return the stats of the actors by note.
     *
     * @return A dataset containing the stats of the actors by note
     */
    DefaultPieDataset getStatsOfActorsByNote();

    /**
     * Return the stats of the realizers by country.
     *
     * @return A dataset containing the stats of the realizers by country
     */
    DefaultPieDataset getStatsOfRealizersByCountry();

    /**
     * Return the stats of the realizers by note.
     *
     * @return A dataset containing the stats of the realizers by note
     */
    DefaultPieDataset getStatsOfRealizersByNote();

    /**
     * Generate the stats.
     */
    void generateStats();
}