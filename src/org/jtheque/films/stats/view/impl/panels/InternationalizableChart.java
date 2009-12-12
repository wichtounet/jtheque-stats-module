package org.jtheque.films.stats.view.impl.panels;

import org.jfree.chart.JFreeChart;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.language.Internationalizable;

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

/**
 * An internationalizable object to keep JFreeChart objects to the good language.
 *
 * @author Baptiste Wicht
 */
final class InternationalizableChart implements Internationalizable {
    private final JFreeChart chart;
    private final String key;

    /**
     * Construct a new InternationalizableChart for a specific chart and i18n key.
     *
     * @param chart The chart to keep internationalizable.
     * @param key   The i18n key.
     */
    InternationalizableChart(JFreeChart chart, String key) {
        super();

        this.chart = chart;
        this.key = key;
    }

    @Override
    public void refreshText() {
        chart.setTitle(Managers.getManager(ILanguageManager.class).getMessage(key));
    }
}