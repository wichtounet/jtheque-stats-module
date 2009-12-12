package org.jtheque.films.stats.view.impl.actions;

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
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.resource.IResourceManager;
import org.jtheque.core.managers.resource.ImageType;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.films.stats.controller.able.IStatsController;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * Action to close the stats view.
 *
 * @author Baptiste Wicht
 */
public final class AcCloseStatsView extends JThequeAction {
    /**
     * Construct a new AcCloseStatsView.
     */
    public AcCloseStatsView() {
        super("stats.actions.close");

        setIcon(Managers.getManager(IResourceManager.class).getIcon(Managers.getCore().getImagesBaseName(), "exit", ImageType.PNG));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Managers.getManager(IBeansManager.class).<IStatsController>getBean("statsController").closeView();
    }
}