/*
 *  Copyright (c) 2025 Metaform Systems, Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems, Inc. - initial API and implementation
 *
 */

package org.eclipse.edc.fleet.xregistry.server;

import org.eclipse.edc.spi.system.ServiceExtension;

/**
 * Provides core Xregistry server extensions.
 */
public class XregistryServerExtension implements ServiceExtension {

    @Override
    public String name() {
        return "XRegistry Server";
    }

}
