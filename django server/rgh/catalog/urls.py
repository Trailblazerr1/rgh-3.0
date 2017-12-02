from django.conf.urls import url
from . import views

urlpatterns = [
    url(
        r'^products/$',
        views.ProductList.as_view(),
        name='product-list'
    ),
    url(
        r'^products/(?P<email>[\w.%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})/$',
        views.ProductDetail.as_view(),
        name='email'
    ),
    url(
        r'^products/(?P<email>[\w.%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4})/reviews/$',
        views.ReviewList.as_view(),
        name='email'
    ),
    url(
        r'^products/(?P<product_id>[0-9]+)/reviews/(?P<review_id>[0-9]+)/$',
        views.ReviewDetail.as_view(),
        name='review-detail'
    ),
    url(
        r'^listall/$',
        views.ProductListt.as_view(),
        name='product-list'
    ),
]

