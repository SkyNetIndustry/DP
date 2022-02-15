export default {
  stairs(state) {
    return state.sensorData;
  },

  idsOfStairs(state) {
    return state.sensorData.map((item) => {
      return item.id;
    });
  },

  countOfStairs(state) {
    return Object.keys(state.sensorData).length;
  },
};
