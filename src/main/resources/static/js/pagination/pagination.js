Vue.component('pagination', {
	template: '#pagination-tmpl',
	props: ['totalRows', 'currentPage', 'pageSize', 'maxPages'],
	data: function(){
		return {
			current: this.currentPage
		}
	},
	watch: {
		totalRows: function(){
			this.end = Math.min(this.totalPages, this.maxPages);
			this.$emit('changed', this.current)
		},
		currentPage: function(newVal){
			this.current = newVal;
		}
	},
	computed: {
		start: function(){
			return Math.min( (Math.floor(this.current / this.maxPages)) * this.maxPages + 1, this.totalPages == 0 ? 1 : this.totalPages );
		},
		end: function(){
			return Math.min( Math.min( (Math.floor(this.current / this.maxPages))*this.maxPages, this.totalPages ) + this.maxPages, this.totalPages);
		},
		totalPages: function(){
			return Math.ceil(this.totalRows / this.pageSize);
		},
		pages: function(){
			var list = [];
			for (var i = this.start; i <= this.end; i++) {
			    list.push(i);
			}
			return list;
		}
	},
	methods: {
		clickNext: function(){
			this.start = Math.min(this.end + 1, this.totalPages);
			this.end = Math.min(this.end + this.maxPages, this.totalPages);
			this.$emit('changed', this.start)
		},
		clickPrev: function(){
			this.start = Math.max(this.start - this.maxPages, 1);
			this.end = Math.min(this.start + this.maxPages - 1, this.totalPages);
			this.$emit('changed', this.start)
		},
		clickPage: function(pageNo){
			this.current = pageNo;
			this.$emit('changed', pageNo)
		}
	}
});