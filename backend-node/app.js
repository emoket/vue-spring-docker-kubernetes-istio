const express = require('express');
const app = express();

app.get('/api/hello/node', (req, res) => {
  res.send(`Hey! It's me, node.js express!!`);
  console.log(`Message sent`);
});

app.listen(8083, () => {
  console.log(`Node express server listening on port 8083`);
});
