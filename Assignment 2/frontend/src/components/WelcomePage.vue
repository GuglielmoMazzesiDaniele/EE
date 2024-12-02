<template>

    <!-- Root div element is required -->
      <div id="explanations">
        lorem ipsum   lorem ipsum   lorem ipsum     lorem ipsum
        lorem ipsum lorem ipsumlorem ipsumlorem ipsumlorem ipsum
        lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum
        lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
        lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
      </div>
      TODO: Write the explanation of the experiment + instructions
      <div>
        <el-button @click="$emit('next')" type="primary">I understand and wish to proceed</el-button>
      </div>

</template>

<script>
export default {
  name: 'WelcomePage',
};
</script>

<style scoped>
#explanations {
  display: flex;
  flex-direction: column;
}

</style>
