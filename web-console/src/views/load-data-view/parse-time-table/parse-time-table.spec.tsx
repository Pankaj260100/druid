/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { render } from '@testing-library/react';

import type { IngestionSpec } from '../../../druid-models';
import { PLACEHOLDER_TIMESTAMP_SPEC } from '../../../druid-models';
import { deepSet } from '../../../utils';
import { JSON_SAMPLE } from '../../../utils/sampler.mock';

import { ParseTimeTable } from './parse-time-table';

describe('ParseTimeTable', () => {
  it('matches snapshot', () => {
    const spec = deepSet(
      {} as IngestionSpec,
      'spec.dataSchema.timestampSpec',
      PLACEHOLDER_TIMESTAMP_SPEC,
    );

    const parseTimeTable = (
      <ParseTimeTable
        sampleBundle={{
          sampleResponse: JSON_SAMPLE,
          spec,
        }}
        columnFilter=""
        possibleTimestampColumnsOnly={false}
        selectedColumnName={undefined}
        onTimestampColumnSelect={() => {}}
      />
    );

    const { container } = render(parseTimeTable);
    expect(container.firstChild).toMatchSnapshot();
  });
});
