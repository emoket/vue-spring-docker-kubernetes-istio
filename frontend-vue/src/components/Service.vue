<template>
  <div class="container">
    <div class="service">
      <hr>
      <h1>{{ title }}</h1>
      <br>

      <div class="card">
        <h5 class="card-header">Spring boot backend</h5>
        <div class="card-body">
          <div class="card-text">{{ springMessage }}</div>
        </div>
      </div>
      <br>
      <div class="card">
        <h5 class="card-header">Node express backend</h5>
        <div class="card-body">
          <div class="card-text">{{ nodeMessage}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'service',

    data () {
      return {
        title: 'Greeting Message From Each Backend',
        springMessage: 'Not working now T-T',
        springErrors: [],
        nodeMessage: 'Not working now T-T',
        nodeErrors: []
      }
    },
    created () {
      this.callRestServiceForSpring()
      this.callRestServiceForNode()
    },
    methods: {
      // Fetches posts when the component is created.
      callRestServiceForSpring () {
        AXIOS.get(`/hello/spring`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.springMessage = response.data
            console.log(response.data)
          })
          .catch(e => {
            this.springErrors.push(e)
          })
      },
      callRestServiceForNode () {
        AXIOS.get(`/hello/node`)
            .then(response => {
              // JSON responses are automatically parsed.
              this.nodeMessage = response.data
              console.log(response.data)
            })
            .catch(e => {
              this.nodeErrors.push(e)
            })
      }
    }
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
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
