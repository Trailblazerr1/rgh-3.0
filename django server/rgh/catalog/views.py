from rest_framework import generics
from .permissions import IsAdminOrReadOnly, IsOwnerOrReadOnly
from rest_framework.permissions import IsAuthenticatedOrReadOnly
from .models import Product, Review
from .serializers import ProductSerializer, ReviewSerializer



class ProductList(generics.ListCreateAPIView):
    #queryset = Product.objects.all()
    serializer_class = ProductSerializer
    #permission_classes = (IsAdminOrReadOnly, )
    #lookup_url_kwarg = 'product_id'

    def get_queryset(self):
        queryset = Product.objects.all()
        #permission_classes = (IsAdminOrReadOnly, )
        email = self.request.query_params.get('email', None)
        location  = self.request.query_params.get('location', None)
        job_type = self.request.query_params.get('job_type', None)
        date_avail = self.request.query_params.get('date_avail', None)
        wage = self.request.query_params.get('wage',None)
        # job_type  = self.request.query_params.get('job_type', None)
        # date_avail  = self.request.query_params.get('date_avail', None)
        # h_wage = self.request.query_params.get('h_wage', None)
        print('========')
        #tt= Review.objects.filter(title=title)
        #pp = Review.objects.filter(rating=rating)
        print(email,location,job_type,date_avail,wage)
        #print(Product.objects.filter(reviews__title='jdjd',reviews__rating=444))
        #print(Product.objects.filter(email__in=tt))
        if email is not None:
            queryset = queryset.filter(email=email)
        if location is not None:
            queryset = queryset.filter(location=location,reviews__job_type=job_type,reviews__date_avail=date_avail,reviews__wage__lt=wage)
        # if job_type is not None:
        #     queryset = queryset.filter(reviews__job_type=job_type)
        # if date_avail is not None:
        #     queryset = queryset.filter(reviews__date_avail=date_avail)
        # if wage is not None:
        #     queryset = queryset.filter(reviews__wage__lt=wage)
        print(queryset)
        return queryset 


class ProductDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
    #permission_classes = (IsAdminOrReadOnly, )
    lookup_field = 'email'
    lookup_url_kwarg = 'email'



class ReviewList(generics.ListCreateAPIView):
    serializer_class = ReviewSerializer
    #permission_classes = (IsAuthenticatedOrReadOnly, )
    lookup_field = 'email'
    lookup_url_kwarg = 'email'

    # def perform_create(self, serializer):
    #     serializer.save(
    #         created_by=self.request.user)

    def get_queryset(self):
        lookup_field = 'email'
        mail = self.kwargs['email']
        print('===========================')
        #print(mail)
        #queryset = Review.objects.filter(email__email=mail)
        queryset = Review.objects.all()
        hire = self.request.query_params.get('hire',None)
        if hire is not None:
            queryset = queryset.filter(hire=hire)
        return queryset




class ReviewDetail(generics.RetrieveUpdateDestroyAPIView):
    serializer_class = ReviewSerializer
    #permission_classes = (IsAuthenticatedOrReadOnly, IsOwnerOrReadOnly)
    lookup_url_kwarg = 'review_id'
    def get_queryset(self):
        review = self.kwargs['review_id']
        return Review.objects.filter(id=review)


class ProductListt(generics.ListCreateAPIView):
    serializer_class = ReviewSerializer
    #permission_classes = (IsAuthenticatedOrReadOnly, )
    # lookup_field = 'email'
    # lookup_url_kwarg = 'email'

    # def perform_create(self, serializer):
    #     serializer.save(
    #         created_by=self.request.user)

    def get_queryset(self):
        queryset = Review.objects.all()
        # lookup_field = 'email'
        # mail = self.kwargs['email']
        mail = self.request.query_params.get('email', None)
        print('===========================')
        #print(mail)
        if mail is not None:
            queryset = Review.objects.filter(email__email=mail)
        # if hire is not None:
        queryset = queryset.filter(hire=1)
        return queryset
    # # def get_queryset(self):        if email is not None:
    #         queryset = Review.objects.filter(email__email=mail)
    #     queryset = queryset.filter(hire=1)
    # queryset = Product.objects.all()
    # serializer_class = ProductSerializer
    # permission_classes = (IsAdminOrReadOnly, )
    # lookup_url_kwarg = 'product_id'
    # username = self.request.query_params.get('name', None)
    # if username is not None:
    #     queryset = queryset.filter(name=username)
    # return queryset
