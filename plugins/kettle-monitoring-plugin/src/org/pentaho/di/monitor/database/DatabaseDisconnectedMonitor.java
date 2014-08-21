/*!
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
* Foundation.
*
* You should have received a copy of the GNU Lesser General Public License along with this
* program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
* or from the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU Lesser General Public License for more details.
*
* Copyright (c) 2002-2014 Pentaho Corporation..  All rights reserved.
*/
package org.pentaho.di.monitor.database;

import org.pentaho.di.core.database.Database;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.extension.ExtensionPoint;
import org.pentaho.di.core.extension.ExtensionPointInterface;
import org.pentaho.di.monitor.IKettleMonitoringEvent;
import org.pentaho.di.monitor.MonitorAbstract;

/**
 * @link http://wiki.pentaho.com/display/EAI/PDI+Extension+Point+Plugins
 */

@ExtensionPoint(
  extensionPointId = "DatabaseConnected",
  id = "DatabaseConnectedMonitor",
  description = "Job metadata was loaded"
)
public class DatabaseDisconnectedMonitor extends MonitorAbstract implements ExtensionPointInterface {

  @Override
  public IKettleMonitoringEvent toKettleEvent( Object o ) throws KettleException {

    if ( o == null || !( o instanceof Database ) ) {
      return null;
    }

    DatabaseEvent event =
      new DatabaseEvent( DatabaseEvent.EventType.DISCONNECTED ).build( ( (Database) o ).getDatabaseMeta() );

    logInfo( "[PDI Extension Point Plugin] Dispathing to Event Bus " + event.toString() );

    return event;
  }
}