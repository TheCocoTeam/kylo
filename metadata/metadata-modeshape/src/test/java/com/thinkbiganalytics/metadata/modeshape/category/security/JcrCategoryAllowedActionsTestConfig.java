/**
 *
 */
package com.thinkbiganalytics.metadata.modeshape.category.security;

/*-
 * #%L
 * thinkbig-metadata-modeshape
 * %%
 * Copyright (C) 2017 ThinkBig Analytics
 * %%
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
 * #L%
 */

import com.thinkbiganalytics.metadata.api.MetadataAccess;
import com.thinkbiganalytics.metadata.api.PostMetadataConfigAction;
import com.thinkbiganalytics.metadata.api.category.CategoryProvider;
import com.thinkbiganalytics.metadata.api.category.security.CategoryAccessControl;
import com.thinkbiganalytics.metadata.modeshape.category.JcrCategoryProvider;
import com.thinkbiganalytics.security.action.AllowedActions;
import com.thinkbiganalytics.security.action.config.ActionsModuleBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;

/**
 *
 */
@Configuration
public class JcrCategoryAllowedActionsTestConfig {

    @Inject
    private ActionsModuleBuilder builder;
    
    @Inject
    private MetadataAccess metadataAccess;

    @Bean
    public PostMetadataConfigAction configAuthorization() {
        return () -> metadataAccess.commit(() -> {
            //@formatter:off

            return builder
                        .module(AllowedActions.CATEGORY)
                            .action(CategoryAccessControl.ACCESS_CATEGORY)
                            .action(CategoryAccessControl.EDIT_SUMMARY)
                            .action(CategoryAccessControl.ACCESS_DETAILS)
                            .action(CategoryAccessControl.EDIT_DETAILS)
                            .action(CategoryAccessControl.DELETE)
                            .action(CategoryAccessControl.CREATE_FEED)
                            .action(CategoryAccessControl.CHANGE_PERMS)
                            .add()
                        .build();

            //@formatter:on
        }, MetadataAccess.SERVICE);
    }
    

    @Bean
    @Primary
    public CategoryProvider categoryProvider() {
        return new JcrCategoryProvider();
    }

}
