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

package org.eclipse.edc.fleet.reconciler.core.manager;

import java.util.stream.Stream;

import org.eclipse.edc.fleet.spi.reconciler.ResourceReconcilerRegistry;
import org.eclipse.edc.spi.monitor.Monitor;

/**
 *
 */
public class ReconciliationManager implements Runnable {
    private ResourceReconcilerRegistry reconcilerRegistry;
    private Monitor monitor;

    public ReconciliationManager(ResourceReconcilerRegistry reconcilerRegistry, Monitor monitor) {
        this.reconcilerRegistry = reconcilerRegistry;
        this.monitor = monitor;
    }


    @Override
    public void run() {
        reconcilerRegistry.getReconcilers().forEach(reconciler -> {
            reconciler.reconcile(Stream.of());
        });
    }
}
