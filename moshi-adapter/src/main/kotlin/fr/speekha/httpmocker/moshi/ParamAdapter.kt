/*
 * Copyright 2019 David Blanc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.speekha.httpmocker.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

internal class ParamAdapter {

    @FromJson
    fun paramFromJson(reader: JsonReader): Map<String, String?> {
        val map = mutableMapOf<String, String?>()
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            val value = if (reader.peek() != JsonReader.Token.NULL) {
                reader.nextString()
            } else {
                reader.nextNull<Unit>()
                null
            }
            map += name to value
        }
        reader.endObject()
        return map
    }

    @ToJson
    fun paramToJson(writer: JsonWriter, headers: Map<String, String?>) {
        writer.beginObject()
        writer.serializeNulls = true
        headers.forEach {
            writer.name(it.key)
            writer.value(it.value)
        }
        writer.serializeNulls = false
        writer.endObject()
    }
}