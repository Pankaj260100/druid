/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.query;

import org.apache.druid.java.util.common.guava.Sequence;
import org.apache.druid.query.context.ResponseContext;
import org.apache.druid.segment.Segment;

/**
 * Wrapper around a {@link QueryRunner} that adds a name and allows retrieval of the underlying segment. Used by
 * {@link QueryRunnerTestHelper#makeQueryRunners(QueryRunnerFactory, boolean)} and related methods.
 */
public class TestQueryRunner<T> implements QueryRunner<T>
{
  private final String name;
  private final QueryRunner<T> runner;
  private final Segment segment;

  public TestQueryRunner(String name, QueryRunner<T> runner, Segment segment)
  {
    this.name = name;
    this.runner = runner;
    this.segment = segment;
  }

  @Override
  public Sequence<T> run(QueryPlus<T> queryPlus, ResponseContext responseContext)
  {
    return runner.run(queryPlus, responseContext);
  }

  @Override
  public Sequence<T> run(QueryPlus<T> queryPlus)
  {
    return runner.run(queryPlus);
  }

  public String getName()
  {
    return name;
  }

  public Segment getSegment()
  {
    return segment;
  }

  @Override
  public String toString()
  {
    return name;
  }
}
