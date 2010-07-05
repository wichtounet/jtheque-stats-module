package org.jtheque.films.stats.services.impl;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.films.stats.services.able.IStatsService;
import org.jtheque.films.stats.services.impl.utils.StatsCalculator;
import org.jtheque.films.stats.services.impl.utils.StatsInformations;
import org.jtheque.utils.bean.Duration;

import java.util.Arrays;

/**
 * A service to manage stats.
 *
 * @author Baptiste Wicht
 */
public final class StatsService implements IStatsService {
    private final ILanguageManager resources = Managers.getManager(ILanguageManager.class);

    /**
     * The stats calculator.
     */
    private StatsInformations stats;

    /**
     * Indicate if the title is up to date or not.
     */
    private boolean titlesUpToDate;

    /**
     * The titles for the headers.
     */
    private String[] titles;

    /**
     * Return the calculator.
     *
     * @return The stats calculator.
     */
    private StatsInformations getCalculator() {
        if (stats == null) {
            stats = StatsCalculator.getInstance();
            stats.generateStats();
        }

        return stats;
    }

    @Override
    public void refreshStats() {
        getCalculator().generateStats();

        titlesUpToDate = false;
    }

    @Override
    public String[] getTitles() {
        if (!titlesUpToDate) {
            titles = new String[3];

            titles[0] = getCalculator().getNumberOfRealizers() + " " +
                    resources.getMessage("stats.view.realizers").toLowerCase(resources.getCurrentLocale());
            titles[1] = getCalculator().getNumberOfActors() + " " +
                    resources.getMessage("stats.view.actors").toLowerCase(resources.getCurrentLocale());
            titles[2] = getCalculator().getNumberOfFilms() + " " +
                    resources.getMessage("stats.view.films").toLowerCase(resources.getCurrentLocale()) +
                    " (" + new Duration(getCalculator().getTotalDurationOfFilms()).toString() + ')';

            titlesUpToDate = true;
        }

        return Arrays.copyOf(titles, titles.length);
    }

    @Override
    public StatsInformations getInformations() {
        return getCalculator();
    }

    /**
     * Set the stats.
     *
     * @param stats The stats informations.
     */
    public void setStats(StatsInformations stats) {
        this.stats = stats;
    }
}
