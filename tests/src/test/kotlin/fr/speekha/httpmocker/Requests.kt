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

package fr.speekha.httpmocker

import okhttp3.Headers.Companion.headersOf
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

@JvmOverloads
fun buildRequest(
    url: String,
    headers: List<Pair<String, String>> = emptyList(),
    method: String = "GET",
    body: String? = null
): Request {
    return Request.Builder()
        .url(url)
        .headers(headersOf(*headers.flatMap { listOf(it.first, it.second) }.toTypedArray()))
        .method(method, body?.toRequestBody("text/plain".toMediaTypeOrNull()))
        .build()
}
