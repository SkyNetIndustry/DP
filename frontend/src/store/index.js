import { createStore } from "vuex";

import stairModule from "./stair/index.js";

const store = createStore({
  modules: {
    stair: stairModule,
  },
});

export default store;
