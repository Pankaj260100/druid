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

package org.apache.druid.msq.statistics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.druid.msq.statistics.serde.KeyCollectorSnapshotSerializer;
import org.apache.druid.msq.statistics.serde.QuantilesSnapshotSerializer;

import java.util.Objects;

@JsonTypeName(QuantilesSketchKeyCollectorSnapshot.TYPE)
public class QuantilesSketchKeyCollectorSnapshot implements KeyCollectorSnapshot
{
  static final String TYPE = "quantile";
  private final String encodedSketch;

  private final double averageKeyLength;

  @JsonCreator
  public QuantilesSketchKeyCollectorSnapshot(@JsonProperty("encodedSketch") String encodedSketch, @JsonProperty("averageKeyLength") double averageKeyLength)
  {
    this.encodedSketch = encodedSketch;
    this.averageKeyLength = averageKeyLength;
  }

  @JsonProperty("encodedSketch")
  public String getEncodedSketch()
  {
    return encodedSketch;
  }

  @JsonProperty("averageKeyLength")
  public double getAverageKeyLength()
  {
    return averageKeyLength;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuantilesSketchKeyCollectorSnapshot that = (QuantilesSketchKeyCollectorSnapshot) o;
    return Objects.equals(encodedSketch, that.encodedSketch)
           && Double.compare(that.averageKeyLength, averageKeyLength) == 0;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(encodedSketch, averageKeyLength);
  }

  @Override
  public KeyCollectorSnapshotSerializer getSerializer()
  {
    return new QuantilesSnapshotSerializer();
  }

  @Override
  public String toString()
  {
    return "QuantilesSketchKeyCollectorSnapshot{" +
           "encodedSketch='" + encodedSketch + '\'' +
           ", averageKeyLength=" + averageKeyLength +
           '}';
  }
}
