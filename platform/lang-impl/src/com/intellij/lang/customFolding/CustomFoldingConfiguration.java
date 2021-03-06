/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package com.intellij.lang.customFolding;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;

/**
 * @author Rustam Vishnyakov
 */
@State(
    name = "CustomFolding",
    storages = {
        @Storage(file = StoragePathMacros.PROJECT_FILE),
        @Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/customFolding.xml", scheme = StorageScheme.DIRECTORY_BASED)
})
public class CustomFoldingConfiguration implements PersistentStateComponent<CustomFoldingConfiguration.State> {
  
  private State myState = new State();
  
  public static CustomFoldingConfiguration getInstance(Project project) {
    return ServiceManager.getService(project, CustomFoldingConfiguration.class);
  }

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(State state) {
    myState = state;
  }

  public static class State {

    private String startFoldingPattern = "";
    private String endFoldingPattern = "";
    private String defaultCollapsedStatePattern = "";
    private boolean isEnabled = false;

    public String getDefaultCollapsedStatePattern() {
      return defaultCollapsedStatePattern;
    }

    public void setDefaultCollapsedStatePattern(String defaultCollapsedStatePattern) {
      this.defaultCollapsedStatePattern = defaultCollapsedStatePattern == null ? "" : defaultCollapsedStatePattern.trim();
    }

    public String getStartFoldingPattern() {
      return startFoldingPattern;
    }

    public void setStartFoldingPattern(String startFoldingPattern) {
      this.startFoldingPattern = startFoldingPattern == null ? "" : startFoldingPattern.trim();
    }

    public String getEndFoldingPattern() {
      return endFoldingPattern;
    }

    public void setEndFoldingPattern(String endFoldingPattern) {
      this.endFoldingPattern = endFoldingPattern == null ? "" : endFoldingPattern.trim();
    }

    public boolean isEnabled() {
      return isEnabled;
    }

    public void setEnabled(boolean enabled) {
      isEnabled = enabled;
    }
  }
}
