from __future__ import unicode_literals
from django.db import models
from django.contrib.auth.models import User


class Product(models.Model):
    # name = models.CharField(max_length=255)
    # description = models.TextField()
    # price = models.DecimalField(decimal_places=2, max_digits=20)
    #for all users
    name = models.CharField(max_length=255)
    email = models.EmailField(max_length=255, unique=True)
    age = models.IntegerField()
    location = models.CharField(max_length=255)
    phone = models.IntegerField()
    def __unicode__(self):
        return '%s' %( self.email)
    def __str__(self):
        return '%s' %( self.email)


    # #for one who hires
    # h_job_type = models.CharField(max_length=255)
    # h_date_avail = models.TextField()
    # h_wage = models.IntegerField()
    # h_if_hired = models.IntegerField(default=0)

    # #for one who gets hired
    # job_type = models.CharField(max_length=255)
    # date_avail = models.TextField()
    # wage = models.IntegerField()
    # if_hired = models.IntegerField(default=0)

    #common


class Review(models.Model):
    email = models.ForeignKey(Product, related_name='reviews')
    # email = models.ForeignKey(Product, related_name='reviews')
    # title = models.CharField(max_length=255)
    # revieww = models.TextField()
    # rating = models.IntegerField()
    job_type = models.CharField(max_length=255)
    date_avail = models.TextField()
    wage = models.IntegerField()
    hire = models.IntegerField(default=0)
    #created_by = models.ForeignKey(User)
    def __str__(self):
        return '%s' %( self.email)
    def __unicode__(self):
        return '%s' %( self.email)  

