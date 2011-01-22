/*
 * Copyright (C) 2008 Google Inc.
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

package com.google.gson;

import java.util.Collection;

/**
 * A wrapper class used to collect numerous {@link ExclusionStrategy2} objects
 * and perform a short-circuited OR operation.
 *
 * @author Joel Leitch
 */
final class DisjunctionExclusionStrategy implements ExclusionStrategy2 {
  private final Collection<ExclusionStrategy2> strategies;

  public DisjunctionExclusionStrategy(Collection<ExclusionStrategy2> strategies) {
    Preconditions.checkNotNull(strategies);
    this.strategies = strategies;
  }

  public boolean shouldSkipField(FieldAttributes f, Mode mode) {
    for (ExclusionStrategy2 strategy : strategies) {
      if (strategy.shouldSkipField(f, mode)) {
        return true;
      }
    }
    return false;
  }

  public boolean shouldSkipClass(Class<?> clazz, Mode mode) {
    for (ExclusionStrategy2 strategy : strategies) {
      if (strategy.shouldSkipClass(clazz, mode)) {
        return true;
      }
    }
    return false;
  }
}
