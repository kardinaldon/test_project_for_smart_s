new Vue({
    el: '#main_bloc',
    data: {
        rendering_shopping_list: true,
        rendering_all_purchases_per_week: false,
        rendering_most_purchased_item_last_month: false,
        rendering_best_customer_in_half_year: false,
        rendering_most_bought_by_eighteen_year_old_customers: false,
        rendering_new_purchase: false,
        purchaseName: '',
        countOfPurchase: '',
        purchaseAmount: '',
        purchases: '',
        purchases_per_week: null,
        most_purchased_items_last_month: null,
        best_customers: null,
        most_purchased_items_eighteen_customers: null
    },

    mounted() {
        var id = this.$cookies.get("id");
        axios.all([axios.get('../front/customer_purchases/' + id),
            axios.get('../front_reports/all_purchases_per_week'),
            axios.get('../front_reports/top_purchase_last_month'),
            axios.get('../front_reports/best_customer_half_year'),
            axios.get('../front_reports/purchased_product_eighteen_customers')])
            .then(axios.spread((firstResponse, secondResponse, thirdResponse, fourthResponse, fifthResponse) => {

                this.purchases = firstResponse.data;
                this.purchases_per_week = secondResponse.data;
                this.most_purchased_items_last_month = thirdResponse.data;
                this.best_customers = fourthResponse.data;
                this.most_purchased_items_eighteen_customers = fifthResponse.data;
            }))
            .catch(error => console.log(error));

    },
    methods: {
        my_purchases: function(event) {
            this.rendering_shopping_list = true;
            this.rendering_all_purchases_per_week = false;
            this.rendering_most_purchased_item_last_month = false;
            this.rendering_best_customer_in_half_year = false;
            this.rendering_most_bought_by_eighteen_year_old_customers = false;
            this.rendering_new_purchase = false;
        },

        per_week: function(event) {
            this.rendering_shopping_list = false;
            this.rendering_all_purchases_per_week = true;
            this.rendering_most_purchased_item_last_month = false;
            this.rendering_best_customer_in_half_year = false;
            this.rendering_most_bought_by_eighteen_year_old_customers = false;
            this.rendering_new_purchase = false;
        },

        last_month: function(event) {
            this.rendering_shopping_list = false;
            this.rendering_all_purchases_per_week = false;
            this.rendering_most_purchased_item_last_month = true;
            this.rendering_best_customer_in_half_year = false;
            this.rendering_most_bought_by_eighteen_year_old_customers = false;
            this.rendering_new_purchase = false;
        },

        in_half_year: function(event) {
            this.rendering_shopping_list = false;
            this.rendering_all_purchases_per_week = false;
            this.rendering_most_purchased_item_last_month = false;
            this.rendering_best_customer_in_half_year = true;
            this.rendering_most_bought_by_eighteen_year_old_customers = false;
            this.rendering_new_purchase = false;
        },

        specific_user_age_purchases: function(event) {
            this.rendering_shopping_list = false;
            this.rendering_all_purchases_per_week = false;
            this.rendering_most_purchased_item_last_month = false;
            this.rendering_best_customer_in_half_year = false;
            this.rendering_most_bought_by_eighteen_year_old_customers = true;
            this.rendering_new_purchase = false;
        },

        new_purchase: function(event) {
            this.rendering_shopping_list = false;
            this.rendering_all_purchases_per_week = false;
            this.rendering_most_purchased_item_last_month = false;
            this.rendering_best_customer_in_half_year = false;
            this.rendering_most_bought_by_eighteen_year_old_customers = false;
            this.rendering_new_purchase = true;
        },

        send_purchase_body: function (event) {
            var xmlBody = '<PurchaseDto>\n' +
                '<purchaseName>' +
                this.purchaseName +
                '</purchaseName>\n' +
                '<countOfPurchase>' +
                this.countOfPurchase +
                '</countOfPurchase>\n' +
                '<purchaseAmount>' +
                this.purchaseAmount +
                '</purchaseAmount>\n' +
                '</PurchaseDto>';
            const config = {
                headers: {'Content-Type': 'application/xml'}
            };
            axios.post('../front/purchase_new', xmlBody, config)
                .then(response => {
                    alert("Purchase created");
                })
                .catch(error => {
                    // console.log(error);
                    // alert(error);
                    alert(error.response.data.message);
                    });
        }
    }
});