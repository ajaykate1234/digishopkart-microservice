<template>
    <h4>Add product</h4>
    <div class="form">
        <form action="">
            <table class="table table-bordered">
                <thead>
                    <th>Fields</th>
                    <th>Items</th>
                </thead>

                <tbody>
                    <tr>
                        <td>
                            <label for="productName">ProductName</label>
                        </td>
                        <td>
                            <input type="text" id="productName" placeholder="Enter Product name" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label for="brand">Brand</label>
                        </td>
                        <td>
                            <input type="text" id="brand" placeholder="Enter Brand name" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label for="productPrice">Price</label>
                        </td>
                        <td>
                            <input type="number" id="productPrice" placeholder="Enter Product Price" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label for="varientName">varientName</label>
                        </td>
                        <td>
                            <input type="text" id="varientName" placeholder="Enter Product varientName" required>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="varientValue">varientValue</label>
                        </td>
                        <td>
                            <input type="text" id="varientValue" placeholder="Enter Product varientValue" required>
                        </td>
                    </tr>

                </tbody>
                
                
            </table>
        </form>
    </div>
</template>