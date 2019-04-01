<template>
  <div class="container">
    <hr />
    <h1>Create User</h1>

    <div class="input-group mb-3">
      <input
        v-model="user.firstName"
        type="text"
        class="form-control"
        placeholder="first name"
      />
      <input
        v-model="user.lastName"
        type="text"
        class="form-control"
        placeholder="last name"
      />
      <div class="input-group-append">
        <button
          type="button"
          class="btn btn-outline-secondary"
          @click="createUser()"
        >
          Create User
        </button>
      </div>
    </div>

    <div v-if="showResponse">
      <h5>User created with Id: {{ response }}</h5>
    </div>
    <br />
    <button
      v-if="showResponse"
      class="btn btn-outline-info"
      @click="retrieveUser()"
    >
      Retrieve user <strong>No.{{ user.id }}</strong> from database
    </button>
    <p></p>
    <h4 v-if="showRetrievedUser">
      {{ retrievedUser.firstName }} {{ retrievedUser.lastName }}
    </h4>
  </div>
</template>

<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";

export default {
  name: "User",

  data() {
    return {
      response: [],
      errors: [],
      user: {
        lastName: "Watson",
        firstName: "Emma",
        id: 0
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  methods: {
    // Fetches posts when the component is created.
    createUser() {
      const params = new URLSearchParams();
      params.append("firstName", this.user.firstName);
      params.append("lastName", this.user.lastName);

      AXIOS.post(`/user`, params)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          this.user.id = response.data;
          console.log(response.data);
          this.showResponse = true;
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    retrieveUser() {
      AXIOS.get(`/user/` + this.user.id)
        .then(response => {
          // JSON responses are automatically parsed.
          this.retrievedUser = response.data;
          // console.log(response.data)
          this.showRetrievedUser = true;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
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
