package org.jtheque.films.stats.controller.impl;

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
