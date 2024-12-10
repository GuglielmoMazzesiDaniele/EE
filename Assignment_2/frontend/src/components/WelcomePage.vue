<template>

    <!-- Root div element is required -->
    <div id="content">

      <div id="explanations">
        TODO: Write the explanation of the experiment + instructions

        lorem ipsum   lorem ipsum   lorem ipsum     lorem ipsum
        lorem ipsum lorem ipsumlorem ipsumlorem ipsumlorem ipsum
        lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum
        lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
        lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum
      </div>
      <div>
        <el-button @click="$emit('next')" type="primary">I understand and wish to proceed</el-button>
      </div>
    </div>


</template>

<script>
export default {
  name: 'WelcomePage',
};
</script>

<style scoped>
#explanations {
/*  display: flex;
  flex-direction: column;
  width: 50vw;*/
  border: 1px solid black;
  //justify-content: center;
}
/*
#content {
  display:flex;
  flex-direction: column;
  align-content: space-between;
  justify-content: space-between;
}
*/

.el-button {
  padding:10px;
}
</style>
