/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2019 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.registry.task;

import org.eclipse.core.runtime.IConfigurationElement;
import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.impl.AbstractContextDescriptor;
import org.jkiss.dbeaver.model.task.DBTTaskConfigurator;
import org.jkiss.dbeaver.registry.RegistryConstants;

/**
 * TaskTypeDescriptor
 */
public class TaskConfiguratorDescriptor extends AbstractContextDescriptor {

    private final TaskTypeDescriptor type;
    private final IConfigurationElement config;
    private final ObjectType implType;

    TaskConfiguratorDescriptor(TaskTypeDescriptor type, IConfigurationElement config) {
        super(config);
        this.type = type;
        this.config = config;
        this.implType = new ObjectType(config, "class");
    }

    @NotNull
    public String getId() {
        return config.getAttribute(RegistryConstants.ATTR_ID);
    }

    @NotNull
    public String getName() {
        return config.getAttribute(RegistryConstants.ATTR_NAME);
    }

    @NotNull
    public TaskTypeDescriptor getType() {
        return type;
    }

    @NotNull
    public DBTTaskConfigurator createConfigurator() throws DBException {
        return implType.createInstance(DBTTaskConfigurator.class);
    }

    @Override
    public String toString() {
        return implType.getImplName();
    }

}
