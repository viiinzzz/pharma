<template>
  <div class="container my-6">
    <h2 class="title is-3 mt-6">Measures</h2>
    <Grid :rows="rows" :columns="columns" :limit="limit" @rowClick="handleRowClick"/>
    <button class="button" @click="handleUploadFile">Upload File</button>
  </div>
</template>

<script setup>
const itemsPerPage = 5;
const maxItemsPerPage = 20;

const measures = useMeasures(); //global state

const data = measures.value.slice(0, maxItemsPerPage);

const rows = ref(data);
const columns = measureColumns;
const limit = ref(itemsPerPage);

const handleUploadFile = () => {

//TODO
//  data.push(...cars.value.splice(2, 1)); //add 3rd car

  rows.value = [...data];
};

const handleRowClick = (args) => {
  console.log(args)
}
</script>
