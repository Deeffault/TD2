package com.example.td2.repositories

import com.example.td2.entities.Organization
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface OrganizationRepository:CrudRepository<Organization,Int> {

}
