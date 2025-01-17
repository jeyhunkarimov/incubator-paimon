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

package org.apache.paimon.table.source;

import org.apache.paimon.annotation.Public;
import org.apache.paimon.data.BinaryRow;
import org.apache.paimon.table.Table;

import java.util.List;
import java.util.Optional;

/**
 * A scan of {@link Table} to generate {@link Split} splits.
 *
 * @since 0.4.0
 */
@Public
public interface TableScan {

    /** Plan splits, throws {@link EndOfScanException} if the scan is ended. */
    Plan plan();

    /** Get partitions from simple manifest entries. */
    List<BinaryRow> listPartitions();

    /**
     * If all files in this split can be read without merging, returns an {@link Optional} wrapping
     * a list of {@link RawFile}s, otherwise returns {@link Optional#empty()}.
     */
    default Optional<List<RawFile>> convertToRawFiles(Split split) {
        return Optional.empty();
    }

    /**
     * Plan of scan.
     *
     * @since 0.4.0
     */
    @Public
    interface Plan {
        List<Split> splits();
    }
}
