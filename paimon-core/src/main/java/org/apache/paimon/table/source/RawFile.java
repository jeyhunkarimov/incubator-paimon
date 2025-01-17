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

import org.apache.paimon.CoreOptions;
import org.apache.paimon.annotation.Public;

import java.util.Objects;

/**
 * A data file from the table which can be read directly without merging.
 *
 * @since 0.6.0
 */
@Public
public class RawFile {

    private final String path;
    private final long offset;
    private final long length;
    private final String format;
    private final long schemaId;

    public RawFile(String path, long offset, long length, String format, long schemaId) {
        this.path = path;
        this.offset = offset;
        this.length = length;
        this.format = format;
        this.schemaId = schemaId;
    }

    /** Path of the file. */
    public String path() {
        return path;
    }

    /** Starting offset of data in the file. */
    public long offset() {
        return offset;
    }

    /** Length of data in the file. */
    public long length() {
        return length;
    }

    /**
     * Format of the file, which is a lower-cased string. See {@link CoreOptions.FileFormatType} for
     * all possible types.
     */
    public String format() {
        return format;
    }

    /** Schema id of the file. */
    public long schemaId() {
        return schemaId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RawFile)) {
            return false;
        }

        RawFile other = (RawFile) o;
        return Objects.equals(path, other.path)
                && offset == other.offset
                && length == other.length
                && Objects.equals(format, other.format)
                && schemaId == other.schemaId;
    }

    @Override
    public String toString() {
        return String.format(
                "{path = %s, offset = %d, length = %d, format = %s, schemaId = %d}",
                path, offset, length, format, schemaId);
    }
}
