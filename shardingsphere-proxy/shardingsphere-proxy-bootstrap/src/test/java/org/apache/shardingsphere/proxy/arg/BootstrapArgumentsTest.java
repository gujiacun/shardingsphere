/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.arg;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class BootstrapArgumentsTest {
    
    @Test
    public void assertNewWithEmptyArgument() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{});
        assertThat(actual.getPort(), is(3307));
        assertThat(actual.getConfigurationPath(), is("/conf/"));
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    @Test(expected = IllegalArgumentException.class)
    public void assertNewWithWrongArgument() {
        String wrongArgument = "WrongArgument";
        try {
            new BootstrapArguments(new String[]{wrongArgument});
        } catch (final IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(String.format("Invalid port `%s`.", wrongArgument)));
            throw ex;
        }
    }
    
    @Test
    public void assertNewWithOneArgumentOnly() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{"3306"});
        assertThat(actual.getPort(), is(3306));
        assertThat(actual.getConfigurationPath(), is("/conf/"));
    }
    
    @Test
    public void assertNewWithTwoArgumentsAndConfigurationPathWithoutSlash() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{"3306", "test_conf"});
        assertThat(actual.getPort(), is(3306));
        assertThat(actual.getConfigurationPath(), is("/test_conf/"));
    }
    
    @Test
    public void assertNewWithTwoArgumentsAndConfigurationPathWithLeftSlash() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{"3306", "/test_conf"});
        assertThat(actual.getPort(), is(3306));
        assertThat(actual.getConfigurationPath(), is("/test_conf/"));
    }
    
    @Test
    public void assertNewWithTwoArgumentsAndConfigurationPathWithRightSlash() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{"3306", "test_conf/"});
        assertThat(actual.getPort(), is(3306));
        assertThat(actual.getConfigurationPath(), is("/test_conf/"));
    }
    
    @Test
    public void assertNewWithTwoArgumentsAndConfigurationPathWithBothSlash() {
        BootstrapArguments actual = new BootstrapArguments(new String[]{"3306", "/test_conf/"});
        assertThat(actual.getPort(), is(3306));
        assertThat(actual.getConfigurationPath(), is("/test_conf/"));
    }
}
