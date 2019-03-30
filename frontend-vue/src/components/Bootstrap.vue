<template>
  <div class="container">
    <div class="bootstrap">
      <hr>
      <h1>{{ title }}</h1>
      <p></p>
      <button class="btn btn-outline-success" @click="callRestService()">/api/hello/spring (GET)</button>
      <p></p>

      <div v-if="showResponse">
        <div class="card">
          <h5 class="card-header">Return Message</h5>
          <div class="card-body">
            <div class="card-text">{{ response }}</div>
          </div>
        </div>
        <br>

        <div class="card">
          <h5 class="card-header">HTTP Status</h5>
          <div class="card-body">
            <p class="card-text">{{ httpStatusCode }} {{ httpStatusText }}</p>
          </div>
        </div>
        <br>
        <div class="card">
          <h5 class="card-header">HTTP Headers</h5>
          <ul class="list-group list-group-flush">
            <li v-for="header of headers" class="list-group-item">{{ header.valueOf() }}</li>
          </ul>
        </div>
        <br>
        <div class="card">
          <h5 class="card-header">Full Request configuration</h5>
          <div class="card-body">
            <p class="card-text">{{ fullResponse.config }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import {AXIOS} from './http-common'

export default {
  name: 'bootstrap',

  data () {
    return {
      title: 'REST Services Call with Bootstrap UI',
      showResponse: false,
      response: '',
      fullResponse: {
        config: {
          foo: '',
          bar: ''
        }
      },
      httpStatusCode: '',
      httpStatusText: '',
      headers: [],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`/hello/spring`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
          console.log(response.data)
          this.httpStatusCode = response.status
          this.httpStatusText = response.statusText
          this.headers = response.headers
          // console.log(response.headers)
          this.fullResponse = response
          this.showResponse = true
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  margin-bottom: 20px;
}

h1, h2, h3 {
  font-weight: normal;
}

a {
  color: #42b983;
}
</style>
