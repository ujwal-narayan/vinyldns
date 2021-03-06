/*
 * Copyright 2018 Comcast Cable Communications Management, LLC
 *
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
 */

package vinyldns.api.crypto

import cats.effect.IO
import com.typesafe.config.Config
import vinyldns.api.VinylDNSConfig
import vinyldns.core.crypto.CryptoAlgebra

object Crypto {

  lazy val instance: CryptoAlgebra = loadCrypto().unsafeRunSync()

  def loadCrypto(cryptoConfig: Config = VinylDNSConfig.cryptoConfig): IO[CryptoAlgebra] =
    CryptoAlgebra.load(cryptoConfig)

  def encrypt(value: String): String = instance.encrypt(value)

  def decrypt(value: String): String = instance.decrypt(value)
}
