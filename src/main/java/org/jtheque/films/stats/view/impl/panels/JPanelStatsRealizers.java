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
 * A stats panel for realizers.
 *
 * @author Baptiste Wicht
 */
public final class JPanelStatsRealizers extends PanelStats {
    private static final long serialVersionUID = 2330748896262486492L;

    private static final double AN_HALF = 0.5;

    /**
     * Construct a new JPanelStatsRealizers.
     */
    public JPanelStatsRealizers() {
        super();

        PanelBuilder builder = new PanelBuilder(this);

        setHeader("stats.realizers.title", getStatsService().getTitles()[0]);

        builder.add(getHeader(), builder.gbcSet(0, 0));

        builder.add(createPieChartPanel("stats.realizers.pie.country", getStatsService().getInformations().getStatsOfRealizersByCountry()),
                builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, AN_HALF));

        builder.add(createPieChartPanel("stats.realizers.pie.note", getStatsService().getInformations().getStatsOfRealizersByNote()),
                builder.gbcSet(0, 2, GridBagUtils.BOTH, GridBagUtils.BASELINE, 1.0, AN_HALF));
    }

    @Override
    public void refreshText() {
        getHeader().setTitle(getResources().getMessage("stats.realizers.title"));
        getHeader().setDescription(getStatsService().getInformations().getNumberOfRealizers() + " " +
                getResources().getMessage("stats.realizers.realizers").toLowerCase(getResources().getCurrentLocale()));
    }
}