package org.jtheque.films.stats.view.impl.panels;

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

import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.utils.ui.GridBagUtils;

/**
 * A stats panel for films.
 *
 * @author Baptiste Wicht
 */
public final class JPanelStatsFilms extends PanelStats {
    private static final long serialVersionUID = 3556875889469725943L;

    private static final double A_THIRD = 0.33;

    /**
     * Construct a new JPanelStatsFilms.
     */
    public JPanelStatsFilms() {
        super();

        PanelBuilder builder = new PanelBuilder(this);

        setHeader("stats.films.title", getStatsService().getTitles()[2]);

        builder.add(getHeader(), builder.gbcSet(0, 0));

        builder.add(createPieChartPanel("stats.films.pie.type", getStatsService().getInformations().getStatsOfFilmByType()),
                builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));

        builder.add(createPieChartPanel("stats.films.pie.kind", getStatsService().getInformations().getStatsOfFilmByKind()),
                builder.gbcSet(0, 2, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));

        builder.add(createPieChartPanel("stats.films.pie.note", getStatsService().getInformations().getStatsOfFilmByNote()),
                builder.gbcSet(0, 3, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, A_THIRD));
    }

    @Override
    public void refreshText() {
        getHeader().setTitle(getResources().getMessage("stats.films.title"));
        getHeader().setDescription(getStatsService().getInformations().getNumberOfFilms() + " " +
                getResources().getMessage("stats.films.films").toLowerCase(getResources().getCurrentLocale()));
    }
}