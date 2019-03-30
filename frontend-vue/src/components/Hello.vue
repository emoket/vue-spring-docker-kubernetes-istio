<template>
  <div class="container">
    <div class="hello">
      <hr>
      <h1>{{ title }}</h1>
      <br>
      <ul>
        <li>REST Services call:</li>
        <li><router-link :to="{ name: 'Service' }" exact target="_blank">/callservice</router-link></li>
        <li>with Bootstrap UI components:</li>
        <li><router-link :to="{ name: 'Bootstrap' }" exact target="_blank">/bootstrap</router-link></li>
        <li>Spring Boot database access:</li>
        <li><router-link :to="{ name: 'User' }" exact target="_blank">/user</router-link></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'hello',
    props: { title: { type: String, required: true } },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2, h3 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  /*display: inline-block;*/
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
