package org.jtheque.films.stats.controller.impl;

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

import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.films.stats.controller.able.IStatsController;
import org.jtheque.films.stats.view.able.IStatsView;

import javax.annotation.Resource;

/**
 * The view controller for the stats view.
 *
 * @author Baptiste Wicht
 */
public final class StatsController extends AbstractController implements IStatsController {
    @Resource
    private IStatsView view;

    @Override
    public IStatsView getView() {
        return view;
    }
}