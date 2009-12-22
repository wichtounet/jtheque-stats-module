package org.jtheque.films.stats.services.impl;

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