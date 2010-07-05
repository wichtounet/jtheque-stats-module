package org.jtheque.films.stats.view.impl.panels;

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
