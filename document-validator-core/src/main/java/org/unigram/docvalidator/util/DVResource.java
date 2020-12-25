/**
 * DocumentValidator
 * Copyright (c) 2013-, Takahiko Ito, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package org.unigram.docvalidator.util;

/**
 * Contains Settings used throughout DocumentValidator.
 */
public final class DVResource {
  /**
   * Constructor.
   *
   * @param validatorConf settings of Validators
   */
  public DVResource(ValidatorConfiguration validatorConf) {
    super();
    this.configuration = validatorConf;
    this.characterTable = new CharacterTable();
  }

  /**
   * Constructor.
   *
   * @param validatorConf settings of Validators.
   * @param characterConf settings of characters and symbols
   */
  public DVResource(ValidatorConfiguration validatorConf,
                    CharacterTable characterConf) {
    super();
    this.configuration = validatorConf;
    this.characterTable = characterConf;
  }

  /**
   * Get Configuration.
   *
   * @return Configuration
   */
  public ValidatorConfiguration getConfiguration() {
    return configuration;
  }

  /**
   * Get CharacterTable.
   *
   * @return CharacterTable
   */
  public CharacterTable getCharacterTable() {
    return characterTable;
  }

  private final ValidatorConfiguration configuration;

  private final CharacterTable characterTable;
}
