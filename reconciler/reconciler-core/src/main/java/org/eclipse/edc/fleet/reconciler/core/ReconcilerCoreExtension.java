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

package org.eclipse.edc.fleet.reconciler.core;

import org.eclipse.edc.fleet.reconciler.core.registry.ResourceReconcilerRegistryImpl;
import org.eclipse.edc.fleet.spi.reconciler.ResourceReconcilerRegistry;
import org.eclipse.edc.fleet.xregistry.model.definition.RegistrySpecification;
import org.eclipse.edc.runtime.metamodel.annotation.Provider;
import org.eclipse.edc.runtime.metamodel.annotation.Setting;
import org.eclipse.edc.spi.system.ServiceExtension;

/**
 * Loads core services that handle resource reconciliation.
 */
public class ReconcilerCoreExtension implements ServiceExtension {

    @Setting(description = "Fleet registry location", key = "edc.fleet.registry")
    private String registryUrl;

    @Override
    public String name() {
        return "Core Reconciler";
    }

    @Provider
    public ResourceReconcilerRegistry getResourceRegistry() {
        return new ResourceReconcilerRegistryImpl();
    }

    @Provider
    public RegistrySpecification getSpecification() {
        return new RegistrySpecification(registryUrl);
    }

}
