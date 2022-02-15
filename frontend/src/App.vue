<template>
  <router-view :class="getMode()"></router-view>
</template>

<script>
export default {
  setup() {},

  methods: {
    getMode() {
      console.log();
      return "bg-dark text-light vh-100";
    },
  },
};
</script>
