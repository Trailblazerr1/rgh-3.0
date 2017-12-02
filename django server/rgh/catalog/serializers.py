from .models import Product, Review
from rest_framework import serializers


class ReviewSerializer(serializers.ModelSerializer):
    email = serializers.ReadOnlyField(source='email.email')

    class Meta:
        model = Review
        fields = ('id', 'job_type', 'date_avail', 'wage' ,'email','hire')


class ProductSerializer(serializers.ModelSerializer):
    reviews = ReviewSerializer(many=True, read_only=True)

    class Meta:
        model = Product
        fields = ('name', 'email', 'age', 'location', 'phone','reviews')
# ,'h_job_type','h_date_avail','h_wage','h_if_hired','job_type','date_avail','wage','if_hired'