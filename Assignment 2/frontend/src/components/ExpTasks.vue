<template>
  <div>
    <!-- Root div element is required -->
    <h1>Task Selection</h1>
    <div v-if="remainingChoices > 0">
      <button @click="handleChoice()">Button 1</button>
      <button @click="handleChoice()">Button 2</button>
      <button @click="handleChoice()">Button 3</button>
      <button @click="handleChoice()">Button 4</button>
    </div>
    <div v-else>
      <p>You've completed your choices!</p>
      <button @click="$emit('done')">Finish</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ExpTasks',
  props: {
    remainingChoices: Number
  },
  methods: {
    handleChoice() {
      // Just decrement remainingChoices without using choice argument
      if (this.remainingChoices > 0) {
        this.$emit('update:remainingChoices', this.remainingChoices - 1);  // Update remaining choices
      }
    }
  }
};
</script>

<style scoped>
/* Add your styles here */
</style>
