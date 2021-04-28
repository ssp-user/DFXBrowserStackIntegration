package com.autotest.testruner;

import cucumber.api.Scenario;

import java.util.HashMap;
import java.util.Map;

    /**
     * Storage for arbitrary data associated with a particular Scenario.
     * Intended to store state between Steps of a Scenario.
     *
     * Created by David Tao  on 02/07/2018.
     */
    public class ScenarioBank {
        private ScenarioBank() {throw new IllegalStateException("ScenarioBank must not be instantiated");}

        private static Scenario currentScenario;
        private static Map<String, Object> scenarioStorage;

        public static void newScenario(Scenario scenario) {
            currentScenario = scenario;
            scenarioStorage = new HashMap<String, Object>();
        }

        public static Scenario getCurrentScenario() {
            return currentScenario;
        }

        public static void putVal(String key, Object value) {
            scenarioStorage.put(key,value);
        }

        public static Object getVal(String key) {
            return scenarioStorage.get(key);
        }

        public static String getString(String key) {
            return (String) getVal(key);
        }
    }
