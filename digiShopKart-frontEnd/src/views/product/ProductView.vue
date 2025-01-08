<template>
    <div class="card">
        <div class="card-header">
            <h4>Products
                <RouterLink to="/product/add" class="btn btn-success float-end">Add Product</RouterLink>
            </h4>
        </div>

        <div class="card-body">
            <!-- <table class="table table-bordered">
                <thead class="align-center">
                    <tr>
                        <th>Id</th>
                        <th>Product Name</th>
                        <th>Brand</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="(product, index) in this.products" :key="index">
                        <td>{{ product.id }}</td>
                        <td>{{ product.productName }}</td>
                        <td>{{ product.brand }}</td>
                        <td>{{ product.productPrice }}</td>
                        <td>{{ product.availability }}</td> 
                        <td>
                            <button class="btn btn-primary">Show Varients</button> &nbsp;&nbsp;
                            <button class="btn btn-success">Edit Product</button> &nbsp;&nbsp;
                            <button class="btn btn-danger">Delete</button>
                        </td>
                    </tr>

                </tbody>
            </table> -->


            <!-- <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>VarientName</th>
                        <th>VarientValue</th>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="varient in this.products.varientList" :key="varient.id">
                        <td>{{ varient.id }}</td>
                        <td>{{ varient.name }}</td>
                        <td>{{ varient.value }}</td>
                    </tr>
                </tbody>
            </table> -->

            <table class="table table-bordered">
                <thead >
                    <tr>
                        <th>Product Id</th>
                        <th>Product Name</th>
                        <th>Brand</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Variants</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in products" :key="product.id">
                        <td>{{ product.id }}</td>
                        <td>{{ product.productName || 'N/A' }}</td>
                        <td>{{ product.brand }}</td>
                        <td>{{ product.productPrice }}</td>
                        <td>{{ product.availability ? 'In Stock' : 'Out Of Stock' }}</td>
                        <td>
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th>Variant Id</th>
                                        <th>Name</th>
                                        <th>Value</th>
                                        <th>Availability</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="variant in product.varientList" :key="variant.id">
                                        <td>{{ variant.id }}</td>
                                        <td>{{ variant.name }}</td>
                                        <td>{{ variant.value }}</td>
                                        <td>{{ variant.availability !== null ? (variant.availability ? 'In Stock' : 'Out Of Stock') : 'N/A' }}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>

                        <td>
                            <button class="btn btn-success">Edit</button> &nbsp;&nbsp;
                            <button class="btn btn-danger">Delete</button>
                        </td>
                       
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


//-------------------------------------------------------------------

export default {

    data() {
        return {
            products: [
                {
                    id: 1,
                    productName: "T-Shirt",
                    brand: "Adidas",
                    productPrice: 1299.0,
                    availability: true,
                    varientList: [],
                },
                {
                    id: 2,
                    productName: null,
                    brand: "Adidas",
                    productPrice: 1699.0,
                    availability: true,
                    varientList: [
                        { id: 6, name: "Size", value: "S", availability: null },
                        { id: 7, name: "Size", value: "M", availability: null },
                        { id: 8, name: "Size", value: "L", availability: null },
                        { id: 9, name: "Size", value: "XL", availability: null },
                        { id: 10, name: "Size", value: "XXL", availability: null },
                    ],
                }]
        };
    },

    mounted() {
        console.log("fdfd");
        this.getProducts();

    },

    methods: {

        getProducts() {
            axios.get(`http://localhost:8082/digi/product/fetchAll?pageNo=0&pageSize=5`).then(res => {
                console.log("Response :", res);
                this.products = res.data.content

            })
        }
    }
}
</script>

<style>
.table-responsive {
    margin-bottom: 15px;
}

th {
    font-weight:bold;
}
</style>