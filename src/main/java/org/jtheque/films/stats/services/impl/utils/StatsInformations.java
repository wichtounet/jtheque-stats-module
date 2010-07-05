package org.jtheque.films.stats.services.impl.utils;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
