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

package org.eclipse.edc.fleet.reconciler.policy;

import static org.eclipse.edc.fleet.xregistry.policy.model.definition.RegistryPolicyDefinitions.createPolicyGroupDefinition;

import org.eclipse.edc.fleet.xregistry.model.definition.RegistrySpecification;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

/**
 * Loads XRegistry policy extensions.
 */
public class ReconcilerPolicyExtension implements ServiceExtension {

    @Inject
    private RegistrySpecification specification;

    @Override
    public String name() {
        return "Policy Reconciler";
    }

    @Override
    public void initialize(ServiceExtensionContext context) {
        specification.registerGroup(createPolicyGroupDefinition());
    }


}
